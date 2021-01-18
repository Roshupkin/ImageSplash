package android.com.roshchupkin.unsplashapp.ui.fragments.Collection

import android.com.roshchupkin.unsplashapp.repository.collection.CollectionRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class CollectionViewModel
@ViewModelInject
constructor(collectionRepository: CollectionRepository) : ViewModel() {
    var collection = collectionRepository.getCollectionImage().cachedIn(viewModelScope)
}