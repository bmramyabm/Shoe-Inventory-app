package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListShoeBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        /**
         * Navigate to the next screen to Shoe Detail Page.
         */
        binding.addShoe.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment)
        )

        sharedViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            shoes?.let {
                addCurrentShoes(it)
            }
        })

        //For Enabling the Menu Option
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout, menu)
    }

    /**
     * Navigate to the login  screen from the menu option.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    /**
     * //Adding the Current shoe specification  to the Shoe List
     */
    private fun addCurrentShoes(shoes: List<Shoe>) {
        shoes.forEach { currentShoe(it) }
    }

    private fun currentShoe(shoe: Shoe) {
        val listItemShoeBinding: ListShoeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_shoe, null, false)

        listItemShoeBinding.shoeName.text = getString(R.string.string_value, "Name:", shoe.name)
        listItemShoeBinding.companyName.text =
            getString(R.string.string_value, "Company:", shoe.company)
        listItemShoeBinding.shoeSize.text = getString(R.string.double_value, "Size:", shoe.size)
        listItemShoeBinding.shoeDescription.text =
            getString(R.string.string_value, "Description:", shoe.description)
        binding.shoeList.addView(listItemShoeBinding.root)

    }

}






