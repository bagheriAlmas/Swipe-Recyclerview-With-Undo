package ir.almasapps.recyclerviewwithswipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.almasapps.recyclerviewwithswipe.Model.User

class UserAdapter(var context:Context ,var list : ArrayList<User>): RecyclerView.Adapter<UserAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtName : TextView = itemView.findViewById(R.id.item_txtName)
        var txtPhone : TextView = itemView.findViewById(R.id.item_txtPhone)
        var imgUser : ImageView = itemView.findViewById(R.id.item_image)

        fun bindData(user: User){
            txtName.text = user.name
            txtPhone.text = user.phone
            imgUser.setImageResource(user.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleteItem (i : Int){
        list.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(i:Int,user: User){
        list.add(i,user)
        notifyDataSetChanged()
    }
}