package com.example.onlineshop.ui.catalog.recyclerView

import com.example.onlineshop.ui.catalog.recyclerView.AdapterDelegate.Companion.productAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter: ListDelegationAdapter<List<DisplayableItem>>(
    productAdapterDelegate()
)