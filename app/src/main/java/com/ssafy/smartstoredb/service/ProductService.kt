package com.ssafy.smartstoredb.service

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import com.ssafy.smartstoredb.db.DBHelper
import com.ssafy.smartstoredb.dto.Comment
import com.ssafy.smartstoredb.dto.Product
import com.ssafy.smartstoredb.dto.User

private const val TAG = "UserService_싸피"
class ProductService(context: Context) : AbstractService(context) {
    val TABLE = "t_product"
    val COLUMNS = arrayOf("id", "name", "type", "price", "img")

    val COMMENT_TABLE = "t_comment"
    val COMMENT_COLUMNS = arrayOf("id", "user_id", "product_id", "rating", "comment")

    fun getProductList() : List<Product> {
        var product = arrayListOf<Product>()

        getReadableDatabase().use { db ->
            db.query(TABLE, COLUMNS, null, null, null, null, null).use {
                if(it.moveToFirst()) {
                    do {
                        product.add( Product(it.getInt(0), it.getString(1), it.getString(2), it.getInt(3), it.getString(4).split(".")[0]))
                    }while(it.moveToNext())
                }
            }
        }

        return product
    }

    fun getProduct(productId:Int) : Product{
        var product = Product()

        getReadableDatabase().use { db ->
            db.query(TABLE, COLUMNS, "id=?", arrayOf(productId.toString()), null, null, null).use {
                if(it.moveToNext()){
                    product = Product(it.getInt(0), it.getString(1), it.getString(2), it.getInt(3), it.getString(4).split(".")[0])
                }
            }
        }

        return product
    }

    fun getProductWithComments(productId:Int) : Product{
        var product = Product()
        getReadableDatabase().use { db ->
            db.query(TABLE, COLUMNS, "id=?", arrayOf(productId.toString()), null, null, null).use {
                if(it.moveToNext()){
                    product = Product(it.getInt(0), it.getString(1), it.getString(2), it.getInt(3), it.getString(4).split(".")[0])
                }
            }
        }
        getReadableDatabase().use { db ->
            db.query(COMMENT_TABLE, COMMENT_COLUMNS, "product_id=?", arrayOf(productId.toString()), null, null, null).use {
                if(it.moveToFirst()) {
                    do {
                        product.comment.add(Comment(it.getInt(0),
                            it.getString(1),
                            it.getInt(2),
                            it.getFloat(3),
                            it.getString(4)))
                    }while(it.moveToNext())
                }
            }
        }

        return product
    }
}