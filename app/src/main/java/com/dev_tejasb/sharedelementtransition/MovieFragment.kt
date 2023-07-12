package com.dev_tejasb.sharedelementtransition

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dev_tejasb.sharedelementtransition.databinding.FragmentMovieBinding
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedElementEnterTransition = MaterialContainerTransform().apply {

            setPathMotion(MaterialArcMotion())
        }
        postponeEnterTransition()
        super.onViewCreated(view, savedInstanceState)

        val movie = Data.movies[navArgs<MovieFragmentArgs>().value.position]
        binding.posterSIV.transitionName = movie.posterPath
        Glide.with(binding.posterSIV)
            .load(movie.posterPath)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(binding.posterSIV)
        binding.yearMTV.text = movie.year.toString()
        binding.titleMTV.text = movie.title
        binding.overviewMTV.text = movie.overview
    }
}
