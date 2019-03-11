package br.com.helpdev.recyclerviewclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.helpdev.recyclerviewclick.R
import br.com.helpdev.recyclerviewclick.model.User

/**
 * User adapter to use in RecyclerView
 *
 * @author Guilherme Biff Zarelli
 */
class UserAdapter(
    //I decided to create a functional interface instead of a new class.
    val onClickItem: (view: View, user: User) -> Unit
) : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user, createOnClickListener(user))
        }
    }

    private fun createOnClickListener(user: User): View.OnClickListener {
        return View.OnClickListener { view ->
            onClickItem(view, user)
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 */
class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.userId == newItem.userId
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.name == newItem.name
}

/**
 * Representation of the User's Item Holder
 */
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var textViewName: TextView = view.findViewById(R.id.name)

    fun bind(user: User, createOnClickListener: View.OnClickListener) {
        textViewName.text = user.name
        itemView.setOnClickListener(createOnClickListener)
    }
}
