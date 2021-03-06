package br.com.rogalabs.postsapi.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import br.com.rogalabs.postsapi.databinding.FragmentCommentBinding

/**
 * This [DialogFragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [CommentViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */

class CommentFragment : DialogFragment() {

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