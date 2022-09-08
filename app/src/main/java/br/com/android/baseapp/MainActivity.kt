package br.com.android.baseapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.android.baseapp.data.remote.dto.Fact
import br.com.android.baseapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.android.baseapp.theme.BaseAppComposeTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import br.com.android.baseapp.data.SampleData
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    //private lateinit var binding: ActivityMainBinding
    //private lateinit var factAdapter: FactAdapter

    private var list: List<Fact> = mutableListOf()

    private val RECYCLER_STATE = "recycler_state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAppComposeTheme {
                Conversation(SampleData.conversationSample)
            }
        }
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)

        //setObservable()

//        if (list.isEmpty()) {
//            viewModel.getFacts()
//        }
    }

//    private fun setObservable() {
//        viewModel.state.observe(this) {
//            when(it.status) {
//                ResponseStatus.SUCCESS -> {
//                    it.data?.let { it1 ->
//                        list = it1
//                        setFactsList(list)
//                    }
//                    binding.pbFacts.visibility = View.GONE
//                }
//                ResponseStatus.ERROR -> {
//                    binding.pbFacts.visibility = View.GONE
//                    it.message?.let { it1 -> showMessage(it1) }
//                }
//                ResponseStatus.LOADING -> {
//                    binding.pbFacts.visibility = View.VISIBLE
//                }
//            }
//        }
//    }

//    private fun setFactsList(list: List<Fact>) {
//        binding.apply {
//            factAdapter = FactAdapter(list)
//            rvFacts.adapter = factAdapter
//        }
//    }

    private fun showMessage(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cat Facts")
        builder.setMessage(msg)
        builder.setNeutralButton("Ok") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

//@Preview(
//    name = "Light Mode",
//    showBackground = true)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewMessageCard() {
//    BaseAppComposeTheme {
//        MessageCard(
//            msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun PreviewConversation() {
    BaseAppComposeTheme {
        Conversation(SampleData.conversationSample)
    }
}