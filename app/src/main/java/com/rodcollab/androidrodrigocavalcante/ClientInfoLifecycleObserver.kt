package com.rodcollab.androidrodrigocavalcante

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ClientInfoLifecycleObserver(
    private val viewModel: ClientInfoVm
) : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.onResume()
    }
}