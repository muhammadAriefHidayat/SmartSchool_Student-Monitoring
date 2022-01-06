import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Payment

class AdapterPaymet(val itemclik: (Payment) -> Unit)
    : RecyclerView.Adapter<AdapterPaymet.viewholder>(){

    fun setDataPayment(items:ArrayList<Payment>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    private val mData = ArrayList<Payment>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_payment,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview: View, val itemclik: (Payment) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val payem = itemview.findViewById<TextView>(R.id.closed_payment)
        val cv = itemview.findViewById<CardView>(R.id.c_cv)
        val text = itemview.findViewById<TextView>(R.id.list_pembayarantext)
        val date = itemview.findViewById<TextView>(R.id.list_pembayaran_tgl)

        fun bind_disney_songs(homeBeritaModel: Payment){
            text.text = homeBeritaModel.text
            date.text = homeBeritaModel.date
            date.setOnClickListener { itemclik(homeBeritaModel)   }
            cv.setOnClickListener {itemclik(homeBeritaModel)  }
            text.setOnClickListener { itemclik(homeBeritaModel) }
            payem.setOnClickListener{itemclik(homeBeritaModel)}
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}


