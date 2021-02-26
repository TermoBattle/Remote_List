package com.itschool.remote_list.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itschool.remote_list.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {


    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Получаем весь экран
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        //Получаем ComposeView, который является содержимым всего экрана.
        val mainScreen = root.findViewById<ComposeView>(R.id.CV_list_screen)

        mainScreen.setContent {
            MaterialTheme {
                ListScreen(viewModel = viewModel)
            }
        }

        return root
    }
}