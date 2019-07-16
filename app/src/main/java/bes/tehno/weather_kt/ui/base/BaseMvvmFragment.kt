package bes.tehno.weather_kt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import bes.tehno.weather_kt.BR

abstract class BaseMvvmFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    abstract fun provideViewModel(): VM
    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        viewModel = provideViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        viewModel.onViewReady()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        viewModel.onPause()
        super.onPause()
    }
}