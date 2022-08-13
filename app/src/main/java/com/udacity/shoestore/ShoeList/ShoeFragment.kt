package com.udacity.shoestore.ShoeList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeBinding
import com.udacity.shoestore.databinding.ShoeListBinding

class ShoeFragment : Fragment() {

    private lateinit var binding :FragmentShoeBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe,container,false)

        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner , androidx.lifecycle.Observer { list->
            if (list.isNotEmpty()) {

                for (shoe in list) {
                    val shoeListBinding: ShoeListBinding =
                        DataBindingUtil.inflate(inflater, R.layout.shoe_list, container, false)
                    shoeListBinding.shoe = shoe
                    binding.shoesList.addView(shoeListBinding.root)
                }
            }
        })

        binding.addButton.setOnClickListener {
            findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeDetailsFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.over_flow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}