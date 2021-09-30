package com.example.catbreeds.catlist.view.pagination

import android.view.ViewGroup

/**
 * Interface to provide required methods for pagination
 */
interface Paginator<T : ViewGroup> {

    var isLoading: Boolean
    var hasMoreDataAvailable: Boolean
    var isError: Boolean

    /**
     * binds view for pagination
     *
     * @param viewGroup view
     */
    fun bind(viewGroup: T, loadMoreCallback: () -> Unit)

    /**
     * unbinds view
     */
    fun unbind()

}