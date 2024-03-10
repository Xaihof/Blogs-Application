package com.example.blogx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blogx.Model.BlogItemModel
import com.example.blogx.databinding.ActivityEditBlogBinding
import com.google.firebase.database.FirebaseDatabase

class EditBlogActivity : AppCompatActivity() {

    private val binding: ActivityEditBlogBinding by lazy {
        ActivityEditBlogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.imageButton.setOnClickListener {
            finish()
        }

        val blogItemModel = intent.getParcelableExtra<BlogItemModel>("blogItem")

        binding.blogTitle.editText?.setText(blogItemModel?.heading)
        binding.blogDescription.editText?.setText(blogItemModel?.post)

        binding.saveBlogButton.setOnClickListener {
            val updatedTitle = binding.blogTitle.editText?.text.toString().trim()
            val updatedDescription = binding.blogDescription.editText?.text.toString().trim()

            if (updatedTitle.isEmpty() || updatedDescription.isEmpty()) {
                Toast.makeText(this, "Please fill all the details.", Toast.LENGTH_SHORT).show()
            } else {
                blogItemModel?.heading = updatedTitle
                blogItemModel?.post = updatedDescription

                if (blogItemModel != null) {
                    updateDataInFrebase(blogItemModel)
                }
            }

        }

    }

    private fun updateDataInFrebase(blogItemModel: BlogItemModel) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("blogs")
        val postId = blogItemModel.postId
        databaseReference.child(postId).setValue(blogItemModel)
            .addOnSuccessListener {
                Toast.makeText(this, "Blog Update Successful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Blog Update Unsuccessful", Toast.LENGTH_SHORT).show()
            }
    }
}