## Sample how to use onClickItem in RecyclerView

The idea of this project is showing how to create a simple onClickItem
in RecyclerView using Kotlin.

This project uses libraries of the JetPack/Ktx. See: [build.gradle](app/build.gradle)

### Basics Steps:

- Configure your item.xml to clickable:

``` xml
        android:clickable="true"
        android:focusable="true"
        android:background="?android:attr/selectableItemBackground"
```

- Define your Listener in your Adapter

``` kotlin
    class UserAdapter(
        //I decided to create a functional interface instead of a new class.
        val onClickItem: (view: View, user: User) -> Unit
    ) : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {
```

- On binding item, create the OnClickListener with the new action

``` kotlin
 override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user, createOnClickListener(user))
        }
    }
    
 private fun createOnClickListener(user: User): View.OnClickListener {
        return View.OnClickListener { view ->
            //Here, We call the functional interface.
            onClickItem(view, user)
        }
    }
```

- On bind method of the holder set the onClickListener in your itemView:

``` kotlin
    fun bind(user: User, onClickListener: View.OnClickListener) {
        // [...] Binding values...
        itemView.setOnClickListener(onClickListener)
    }
``` 

- At last, implement in your Activity:

``` kotlin
    private fun createAdapter(): UserAdapter {
        return UserAdapter { view: View, user: User ->
            //TODO onClickListener
            Snackbar.make(view, user.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
```