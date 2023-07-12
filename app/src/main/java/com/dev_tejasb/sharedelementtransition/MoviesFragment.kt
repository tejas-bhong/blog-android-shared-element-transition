package com.dev_tejasb.sharedelementtransition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.dev_tejasb.sharedelementtransition.databinding.FragmentMoviesBinding
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedElementReturnTransition = MaterialContainerTransform().apply {

            setPathMotion(MaterialArcMotion())
        }
        postponeEnterTransition()
        super.onViewCreated(view, savedInstanceState)

        binding.root.adapter = MoviesAdapter(Data.movies) { position: Int, sharedView: View ->

            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(
                    position
                ), FragmentNavigatorExtras(sharedView to sharedView.transitionName)
            )
        }
        binding.root.doOnPreDraw {

            startPostponedEnterTransition()
        }
    }
}
