package com.example.meterly.repository

import com.example.meterly.model.Payment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class PaymentRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private var paymentListener: ListenerRegistration? = null

    fun observePayments(
        addressId: String,
        onChanged: (List<Payment>) -> Unit
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        paymentListener?.remove()

        paymentListener = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot == null)
                    return@addSnapshotListener

                val payments = snapshot.documents.mapNotNull { doc ->
                    val payment = doc.toObject(Payment::class.java)
                    if (payment != null) {
                        // ОБХІД БАГУ KOTLIN+FIREBASE: витягуємо булеве значення вручну
                        val actualIsPaid = doc.getBoolean("isPaid") ?: doc.getBoolean("paid") ?: false
                        payment.copy(id = doc.id, isPaid = actualIsPaid)
                    } else null
                }

                onChanged(payments)
            }
    }

    suspend fun savePayment(payment: Payment): String {
        val phone = auth.currentUser?.phoneNumber ?: return ""

        val paymentsRef = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(payment.addressId)
            .collection("payments")

        val utilityValue = try {
            (payment.utilityType as Enum<*>).name
        } catch (e: Exception) {
            payment.utilityType.toString()
        }

        val existing = paymentsRef
            .whereEqualTo("utilityType", utilityValue)
            .get()
            .await()

        val match = existing.documents.firstOrNull { doc ->
            doc.getLong("month") == payment.month.toLong()
                    && doc.getLong("year") == payment.year.toLong()
        }

        return if (match != null) {
            val docId = match.id

            val updateData = mapOf(
                "monthBegin" to payment.monthBegin,
                "monthEnd" to payment.monthEnd,
                "consumption" to payment.consumption,
                "amountDue" to payment.amountDue,
                "rate" to payment.rate,
                "date" to payment.date
            )
            paymentsRef.document(docId).set(updateData, SetOptions.merge()).await()
            docId
        } else {
            val newRef = paymentsRef.document()
            newRef.set(payment.copy(id = newRef.id)).await()
            newRef.id
        }
    }

    suspend fun getPaymentByMonth(
        addressId: String,
        utilityType: String,
        month: Int,
        year: Int
    ): Payment? {
        val phone = auth.currentUser?.phoneNumber ?: return null

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .whereEqualTo("utilityType", utilityType)
            .get()
            .await()

        val match = snapshot.documents.firstOrNull { doc ->
            doc.getLong("month") == month.toLong() && doc.getLong("year") == year.toLong()
        } ?: return null

        val payment = match.toObject(Payment::class.java) ?: return null
        val actualIsPaid = match.getBoolean("isPaid") ?: match.getBoolean("paid") ?: false
        return payment.copy(id = match.id, isPaid = actualIsPaid)
    }

    suspend fun updatePaidStatus(
        addressId: String,
        paymentId: String,
        isPaid: Boolean
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        val statusMap = mapOf(
            "isPaid" to isPaid,
            "paid" to isPaid
        )

        firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .document(paymentId)
            .set(statusMap, SetOptions.merge())
            .await()
    }

    suspend fun updateReceipt(
        addressId: String,
        paymentId: String,
        uri: String,
        fileName: String
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .document(paymentId)
            .update(
                mapOf(
                    "receiptUri" to uri,
                    "receiptFileName" to fileName
                )
            )
            .await()
    }

    suspend fun getPaymentsForUtility(
        addressId: String,
        utilityType: String
    ): List<Payment> {
        val phone = auth.currentUser?.phoneNumber ?: return emptyList()

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .whereEqualTo("utilityType", utilityType)
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .await()

        return snapshot.documents.mapNotNull { doc ->
            val payment = doc.toObject(Payment::class.java)
            if (payment != null) {
                val actualIsPaid = doc.getBoolean("isPaid") ?: doc.getBoolean("paid") ?: false
                payment.copy(id = doc.id, isPaid = actualIsPaid)
            } else null
        }
    }

    fun removeListener() {
        paymentListener?.remove()
        paymentListener = null
    }
}