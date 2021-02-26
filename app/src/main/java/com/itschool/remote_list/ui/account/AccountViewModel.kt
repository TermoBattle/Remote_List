package com.itschool.remote_list.ui.account

import androidx.lifecycle.ViewModel
import com.itschool.remote_list.model.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    val model:Model
) : ViewModel()