package com.example.blogx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.blogx.Model.BlogItemModel
import com.example.blogx.databinding.ActivityReadMoreBinding

class ReadMoreActivity : AppCompatActivity() {

    private val binding: ActivityReadMoreBinding by lazy {
        ActivityReadMoreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }


        val blogs = intent.getParcelableExtra<BlogItemModel>("blogItem")

        if (blogs != null) {
            // Retrieve user data here eg. blog title etx.
            binding.titleText.text = blogs.heading
            binding.userName.text = blogs.userName
            binding.date.text = blogs.date
            binding.blogDescriptionTextView.text = blogs.post

            val userImageUrl = blogs.profileImage
            Glide.with(this)
                .load(userImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.profileImage)


        } else {
            Toast.makeText(this, "Failed to load blogs", Toast.LENGTH_SHORT).show()
        }
    }
}