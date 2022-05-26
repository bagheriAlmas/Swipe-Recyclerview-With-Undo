package ir.almasapps.recyclerviewwithswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.almasapps.recyclerviewwithswipe.Data.DataService
import ir.almasapps.recyclerviewwithswipe.Model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = UserAdapter(this, DataService.users)
        var deleteUser : User

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        val swipeGesture = object :SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var position = viewHolder.absoluteAdapterPosition
                deleteUser = DataService.users[position]

                when(direction){
                    ItemTouchHelper.LEFT -> {
                        DataService.users.removeAt(position)
                        adapter.notifyItemRemoved(position)

                        Snackbar.make(recyclerview,deleteUser.name,Snackbar.LENGTH_LONG).setAction("Undo", View.OnClickListener {
                                DataService.users.add(position,deleteUser)
                                adapter.notifyItemInserted(position)
                            }).show()
                    }


                    ItemTouchHelper.RIGHT -> {
                        DataService.users.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(recyclerview)

    }
}