package br.com.helpdev.recyclerviewclick

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.helpdev.recyclerviewclick.adapter.UserAdapter
import br.com.helpdev.recyclerviewclick.model.User
import br.com.helpdev.recyclerviewclick.util.USERS_DATA_SAMPLE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Sample activity to display a RecyclerView
 *
 * @author Guilherme Biff Zarelli
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userAdapter = createAdapter()
        userAdapter.submitList(USERS_DATA_SAMPLE)

        recycler_view.adapter = userAdapter
    }

    private fun createAdapter(): UserAdapter {
        return UserAdapter { view: View, user: User ->
            //TODO onClickListener
            Snackbar.make(view, user.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}
