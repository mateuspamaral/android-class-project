package br.com.rogalabs.postsapi.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.rogalabs.postsapi.databinding.FragmentCommentBinding

class CommentFragment : Fragment() {

    private val viewModel: CommentViewModel by lazy {
        ViewModelProvider(this).get(CommentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentCommentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val post = CommentFragmentArgs.fromBundle(requireArguments()).selectedPost
        val viewModelFactory = CommentViewModelFactory(post, application)

        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(CommentViewModel::class.java)

        binding.commentList.adapter = CommentAdapter()

        return binding.root
    }

}