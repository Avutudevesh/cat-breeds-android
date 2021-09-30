package com.example.catbreeds.catdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catbreeds.R
import com.example.catbreeds.databinding.CatDetailsFragmentBinding
import com.example.catbreeds.models.CatsBreed
import com.example.catbreeds.utils.instanceOf
import com.example.catbreeds.utils.loadImage

class CatBreedDetailsFragment : Fragment(R.layout.cat_details_fragment) {

    companion object {
        private const val CAT_BREED_DETAILS = "cat_breed"
        fun newInstance(catBreed: CatsBreed) =
            instanceOf<CatBreedDetailsFragment>(CAT_BREED_DETAILS to catBreed)
    }

    private var _binding: CatDetailsFragmentBinding? = null

    private val binding get() = _binding!!

    private var catBreed: CatsBreed? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catBreed = arguments?.getParcelable(CAT_BREED_DETAILS)
        setUpDetailsPage()
    }

    private fun setUpDetailsPage() {
        catBreed?.let {
            binding.catDescription.text = it.description
            binding.catLifespan.text = getString(R.string.life_span, it.life_span)
            binding.catOrigin.text = getString(R.string.origin, it.origin)
            binding.catName.text = it.name
            binding.catImage.loadImage(catBreed?.image?.url.orEmpty(), requireContext())
            binding.alternateNames.text = getString(R.string.alternate_names, it.alt_names)
            binding.catWeight.text = getString(R.string.weight, it.weight.metric)
            binding.catTemperament.text = it.temperament
            it.wikipedia_url?.let { url ->
                binding.learnMoreLayout.setOnClickListener {
                    openUrl(url)
                }
            } ?: kotlin.run {
                binding.learnMoreLayout.visibility = View.GONE
            }

        }
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}