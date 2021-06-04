package com.example.shop.view.bottomNavFragments.UserAction

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.R
import com.example.shop.databinding.FragmentUserBinding
import com.example.shop.view.Auth.AuthActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class UserFragment : Fragment(), View.OnClickListener {

    @Inject
    @Named("UserFragment")
    lateinit var bannerAdapter: BaseBannerAdapter
    private lateinit var binding: FragmentUserBinding
    private lateinit var user_id: String
    private lateinit var user_status: String
    private lateinit var user_name: String
    private lateinit var user_mobile: String
    var sharedPref: SharedPreferences? = null
    var bottomSheetDialog: BottomSheetDialog? = null
    var bottomSheetView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        selectedViews()

    }

    fun initView() {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        user_status = sharedPref!!.getString("status", "logout").toString()
        user_name = sharedPref!!.getString("name", "").toString()
        user_mobile = sharedPref!!.getString("mobile", "").toString()
        user_id = sharedPref!!.getString("id", null).toString()
        when (user_status) {
            "login" -> {
                binding.orginalLayout.visibility = View.VISIBLE
                binding.registerLayout.visibility = View.GONE
            }
            "logout" -> {
                binding.orginalLayout.visibility = View.GONE
                binding.registerLayout.visibility = View.VISIBLE
            }
        }
        binding.txtPhoneNumber.text = user_mobile
        binding.txtUsername.text = user_name

        //set slider adapter
        binding.Banner.setAdapter(bannerAdapter)


    }


    override fun onClick(v: View?) {
        when (v!!.id) {

            binding.txtUserInformation.id -> {
                findNavController().navigate(R.id.action_userFragment_to_userInforamtionFragment)
            }
            binding.txtAddress.id -> {
                findNavController().navigate(R.id.action_userFragment_to_addressFragment)
            }
            binding.txtAuth.id -> {

                requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                requireActivity().finish()
            }
            binding.txtLogout.id -> {

                requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                sharedPref!!.edit().apply {
                    putString("status", "logout").apply()
                    putString("name", null).apply()
                    putString("mobile", null).apply()
                    putString("id", null).apply()
                }
                requireActivity().finish()
            }
            binding.txtOrders.id -> {
                findNavController().navigate(R.id.action_userFragment_to_ordersFragment)
            }
            binding.txtAboutUs.id -> {
                setupAboutUsDialog()
            }

            binding.txtContactUs.id -> {
                setupContactUsDialog()
            }
        }


    }

    fun selectedViews() {
        binding.txtUserInformation.setOnClickListener(this)
        binding.txtAddress.setOnClickListener(this)
        binding.txtAuth.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.txtOrders.setOnClickListener(this)
        binding.txtAboutUs.setOnClickListener(this)
        binding.txtContactUs.setOnClickListener(this)
    }

    private fun callPhoneNumber() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + "+989164408396")
        requireActivity().startActivity(callIntent)
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data =
            Uri.parse("mailto:" + "mohammadta7879@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "پشتیبانی فروشگاه داروهای گیاهی")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun setupAboutUsDialog() {
        bottomSheetView = layoutInflater.inflate(
            R.layout.dialog_about_us,
            requireActivity().findViewById<LinearLayout>(R.id.bottom_sheet_about_us)
        )
        bottomSheetDialog!!.setContentView(bottomSheetView!!)
        bottomSheetDialog!!.show()
    }

    private fun setupContactUsDialog() {
        bottomSheetView = layoutInflater.inflate(
            R.layout.dialog_contact_us,
            requireActivity().findViewById<LinearLayout>(R.id.bottom_sheet_contact_us)
        )
        bottomSheetDialog!!.setContentView(bottomSheetView!!)
        bottomSheetView
        bottomSheetView!!.findViewById<CardView>(R.id.contact_us_mobile).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                callPhoneNumber()
            }
        }
        bottomSheetView!!.findViewById<CardView>(R.id.contact_us_email).setOnClickListener {
            sendEmail()
        }

        bottomSheetDialog!!.show()
    }
}