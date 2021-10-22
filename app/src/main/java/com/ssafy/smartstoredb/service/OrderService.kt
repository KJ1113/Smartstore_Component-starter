package com.ssafy.smartstoredb.service

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import com.ssafy.smartstoredb.db.DBHelper
import com.ssafy.smartstoredb.dto.*

private const val TAG = "OrderService_싸피"
class OrderService(context: Context) : AbstractService(context) {
    val TABLE = "t_order"
    val COLUMNS = arrayOf("o_id", "user_id", "order_table", "order_time", "completed")

    val TABLE_DETAIL = "t_order_detail"
    val DETAIL_COLUMNS = arrayOf("d_id", "order_id", "product_id", "quantity")


    fun getOrderWithDetails(orderId:Int) : Order {
        var order = Order()
        getReadableDatabase().use { db ->
            db.query(TABLE, COLUMNS, "o_id=?", arrayOf(orderId.toString()), null, null, null).use {
                if(it.moveToNext()){
                    order = Order(it.getInt(0),
                        it.getString(1),
                        it.getString(2),
                        it.getString(3),
                        it.getString(4))
                }
            }
        }
        getReadableDatabase().use { db ->
            db.query(TABLE_DETAIL, DETAIL_COLUMNS, "order_id=?", arrayOf(orderId.toString()), null, null, null).use {
                if(it.moveToFirst()) {
                    do {
                        var quantity = it.getInt(3)
                        var productId = it.getInt(2)
                        var detail = OrderDetail(it.getInt(0),
                            it.getInt(1),
                            productId,
                            quantity)

                        //단가, 이미지, 상품명 추가
                        var product = ProductService(context).getProduct(productId)
                        detail.unitPrice = product.price
                        detail.img = product.img
                        detail.productName = product.name
                        detail.productType = product.type

                        //토탈합계 계산
                        order.totalPrice += quantity * product.price
                        order.totalQnanty += quantity
                        //  대표상품 세팅
                        if( order.topProductName == "") order.topProductName = detail.productName
                        if( order.topImg == "") order.topImg = detail.img


                        order.details.add( detail )

                    }while(it.moveToNext())
                }
            }
        }



        return order
    }


}