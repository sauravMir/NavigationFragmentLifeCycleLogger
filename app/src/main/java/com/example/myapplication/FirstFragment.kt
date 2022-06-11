package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: Frgment1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("view Created")
        viewModel = ViewModelProvider(this).get(Frgment1ViewModel::class.java)
        binding.buttonFirst.setOnClickListener {
            findNavController().popBackStack(R.id.action_FirstFragment_to_SecondFragment, false)
        }

        binding.buttonSec.setOnClickListener {
            buildAlert()
        }

        for (i in 1..10){
            viewModel.data.observe(viewLifecycleOwner, Observer {
                log("the livedata: $it")
            })

            // as the log is different the live data will be called 20 times
            viewModel.data.observe(viewLifecycleOwner, Observer {
                log("the livedata2: $it")
            })
        }

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        log("onViewStateRestored")
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        log("onActivityCreated")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        log("onLowMemory")
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        log("onInflate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState")
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
        log("onPrimaryNavigationFragmentChanged")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }


    override fun onStop() {
        super.onStop()
        log("stop")
    }

    override fun onPause() {
        super.onPause()
        log("pause")
    }
    override fun onDestroy() {
        super.onDestroy()
        log("destroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        log("destroyView")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun log(str: String) {
        Log.e("1st", str)
    }

    // this dialog will not make the fragment pause
    // the pausing from dialog will only happen when the dialog is from the system i,e permission dialog
    private fun buildAlert() {
        AlertDialog.Builder(context)
            .setTitle("Delete entry")
            .setMessage("Are you sure you want to delete this entry?") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialog, which ->
                    viewModel.setData("chill")
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}
