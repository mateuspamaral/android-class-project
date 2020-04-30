package br.com.rogalabs.postsapi.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.rogalabs.postsapi.databinding.FragmentPostsListBinding

class PostListFragment : Fragment() {

    private val viewModel: PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentPostsListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.postsList.adapter = PostListAdapter(PostListAdapter.OnClickListener {
            viewModel.displayPostDetails(it)
        })

        viewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController()
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPostDetailsComplete()
            }
        })

        return binding.root
    }
}
