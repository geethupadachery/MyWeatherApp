package sjsu.cmpe277.myandroidmulti

import android.app.Dialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import sjsu.cmpe277.myandroidmulti.databinding.SettingsFragmentBinding


class SettingsFragment : Fragment() {
    private lateinit var binding : SettingsFragmentBinding
    private lateinit var viewModel: SettingsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<SettingsFragmentBinding>(inflater,
            R.layout.settings_fragment,container,false)

        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        binding.edit.setOnClickListener { view: View ->
            val action = SettingsFragmentDirections.actionSettingsFragmentToEdit()
            view.findNavController().navigate(action)
        }


        binding.submit.setOnClickListener() {
            Toast.makeText(
                context?.applicationContext,
                "Submitted comments successfully",
                Toast.LENGTH_LONG
            ).show()
        }
        binding.floatingActionButton.setOnClickListener { view ->
            Snackbar.make(view, "Notifications", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

        }
            //return inflater.inflate(R.layout.settings_fragment, container, false)
            return binding.root
        }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

    }
    /*fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton(R.string.signin,
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog().cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }*/


}
