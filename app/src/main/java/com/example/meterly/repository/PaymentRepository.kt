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
                    doc.toObject(Payment::class.java)?.copy(id = doc.id)
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
            .whereEqualTo("month", payment.month.toLong())
            .whereEqualTo("year", payment.year.toLong())
            .get()
            .await()

        val match = existing.documents.firstOrNull()

        return if (match != null) {
            val docId = match.id
            val updateData = mapOf(
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

    suspend fun updatePaidStatus(
        addressId: String,
        paymentId: String,
        isPaid: Boolean
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .collection("payments")
            .document(paymentId)
            .set(mapOf("isPaid" to isPaid), SetOptions.merge())
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
            doc.toObject(Payment::class.java)?.copy(id = doc.id)
        }
    }

    fun removeListener() {
        paymentListener?.remove()
        paymentListener = null
    }
}