package com.udacity.shoestore.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeList.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding:FragmentShoeDetailsBinding
    private val viewModel : ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_details,container,false)

        binding.shoe= Shoe("","","","")

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeFragment())
        }

        binding.saveButton.setOnClickListener {
            if (isEmpty()){
                Toast.makeText(context,"check all fields was filled",Toast.LENGTH_LONG).show()
            }else{
                viewModel.addShoe(binding.shoe!!)
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeFragment())          }
        }

        return binding.root
    }

    private fun isEmpty():Boolean{
        return binding.shoeNameId.text.isEmpty()||
                binding.shoeCompanyID.text.isEmpty() ||
                binding.shoeSizeId.text.isEmpty() ||
                binding.shoeDescriptionId.text.isEmpty()
    }

}