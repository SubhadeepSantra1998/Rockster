package com.example.rockstar.feature.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rockstar.common.component.IconButtonComponent
import com.example.rockstar.common.component.LabelTextComponent
import com.example.rockstar.common.component.TitleTextComponent
import com.example.rockstar.feature.home.data.model.Item
import com.example.rockstar.feature.home.data.model.Product
import com.example.rockstar.feature.home.data.model.itemList
import com.example.rockstar.feature.home.data.model.tabItems
import com.example.rockstar.feature.home.presentation.components.ProductCardComponent
import com.example.rockstar.feature.viewModel.SharedViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    sharedViewModel: SharedViewModel,

    ) {


    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState()

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        LabelTextComponent(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = null
                        )
                    }
                )
            }
        }


        HorizontalPager(
            pageCount = tabItems.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->

            var filteredList = itemList.filter {
                it.category == tabItems[index].title
            }
            ProductListComponent(filteredList, sharedViewModel)

        }

    }
}

@Composable
fun ProductListComponent(filteredList: List<Product>, sharedViewModel: SharedViewModel) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(filteredList) { data ->
                ProductCardComponent(data.item, sharedViewModel)
            }
        }
    )
}


