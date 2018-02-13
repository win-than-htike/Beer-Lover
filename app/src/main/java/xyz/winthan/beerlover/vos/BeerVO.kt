package xyz.winthan.beerlover.vos
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by winthanhtike on 11/14/17.
 */

@Entity(tableName = "beers")
data class BeerVO(
		@PrimaryKey @SerializedName("id") var id: Int = 0, //1
		@SerializedName("name") var name: String = "", //Buzz
		@SerializedName("tagline") var tagline: String = "", //A Real Bitter Experience.
		@SerializedName("first_brewed") var firstBrewed: String = "", //09/2007
		@SerializedName("description") var description: String = "", //A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.
		@SerializedName("image_url") var imageUrl: String = "" //https://images.punkapi.com/v2/keg.png
)