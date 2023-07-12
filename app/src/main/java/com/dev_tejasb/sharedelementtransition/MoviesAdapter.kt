package com.dev_tejasb.sharedelementtransition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev_tejasb.sharedelementtransition.databinding.ItemMovieBinding

class MoviesAdapter(
    private val movies: Array<Movie>,
    private val onClick: (position: Int, view: View) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemViewBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(position: Int) {

            val movie = movies[position]
            itemViewBinding.root.setOnClickListener { onClick(position, itemViewBinding.posterSIV) }
            itemViewBinding.posterSIV.transitionName = movie.posterPath
            Glide.with(itemViewBinding.posterSIV)
                .load(movie.posterPath)
                .into(itemViewBinding.posterSIV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}
