package ifrs.ibiruba.httpbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_books_list.*
import kotlinx.android.synthetic.main.item_book.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        listView.setOnItemClickListener { parent, view, position, id ->
            val book = parent.getItemAtPosition(position) as? Book
            if (book != null) {
                Toast.makeText(this, "${book.title}, \n ${book.author}, \n ${book.year} ", Toast.LENGTH_LONG).show()


        }
    }

        

}



}




