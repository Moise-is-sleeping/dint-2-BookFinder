package ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.calculator.bookfinder.friendbutton.FriendButton
import com.calculator.bookfinder.friendbutton.lindenHill
import com.calculator.bookfinder.friendslistitem.FriendsListItem
import com.calculator.bookfinder.friendslistitem.Property1
import com.calculator.bookfinder.header.Header
import com.calculator.bookfinder.header.lancelot
import com.calculator.bookfinder.morebuttons.MoreButtons
import com.calculator.bookfinder.naviagtionbar.NaviagtionBar
import com.calculator.bookfinder.searchfield.TopLevel
import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText
import com.google.relay.compose.relayDropShadow
import data.Routes.Routes
import ui.ViewModel.BookDatabaseViewModel
import ui.ViewModel.BookViewModel
import ui.ViewModel.UserInteractionViewmodel

@Composable
fun FriendsScreen(bookViewModel: BookViewModel, navController: NavController, bookDatabaseViewModel: BookDatabaseViewModel,userInteractionViewmodel: UserInteractionViewmodel){
    var counter by remember { mutableIntStateOf(0) }
    var moreButton by remember { mutableStateOf(false) }
    val currentScreen by userInteractionViewmodel.currentFriendsButton.collectAsState()



    Text(text = counter.toString())
    Column (modifier= Modifier
        .fillMaxSize()
        .background(color = Color(bookViewModel.backgroundColor())),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
        Header(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
        Spacer(modifier = Modifier.fillMaxHeight(0.025f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(62.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                FriendButtonEdit(
                    buttonName = "Find Friends",
                    modifier = Modifier
                        .rowWeight(1.0f)
                        .columnWeight(1.0f)
                        .fillMaxHeight(0.6f)
                        .weight(1f)
                        .clickable {
                            userInteractionViewmodel.currentButton(1)
                            counter += 1
                        }, color = userInteractionViewmodel.buttonColor(1)
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                FriendButtonEdit(
                    buttonName = "Your Friends",
                    modifier = Modifier
                        .rowWeight(1.0f)
                        .columnWeight(1.0f)
                        .fillMaxHeight(0.6f)
                        .weight(1f)
                        .clickable {
                            userInteractionViewmodel.currentButton(2)
                            counter += 1
                        }, color = userInteractionViewmodel.buttonColor(2)
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                FriendButtonEdit(
                    buttonName = "Requests",
                    modifier = Modifier
                        .rowWeight(1.0f)
                        .columnWeight(1.0f)
                        .fillMaxHeight(0.6f)
                        .weight(1f)
                        .clickable {
                            userInteractionViewmodel.currentButton(3)
                            counter += 1
                        }, color = userInteractionViewmodel.buttonColor(3)
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
            }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.915f),
            horizontalAlignment = Alignment.CenterHorizontally){
            when(currentScreen){
                1->{
                    findFriendsScreen(userInteractionViewmodel)
                    UsernamesResults(userInteractionViewmodel,navController)


                }
                2->{
                    userInteractionViewmodel.getFriends()
                    CurrentFriendsScreen(userInteractionViewmodel,navController)

                }
                3->{

                }
            }

        }


        NaviagtionBar(

            homebutton = {navController.navigate(Routes.HomeScreen.route)},
            searchButton = {navController.navigate(Routes.SearchScreen.route)},
            savedButton = {
                bookDatabaseViewModel.fetchBooks()
                navController.navigate(Routes.SavedScreen.route)
            },
            moreButton = {moreButton=true},
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
                .fillMaxWidth()
        )
    }

    if (moreButton){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
            Box(
                modifier = Modifier
                    .width(205.dp)
                    .height(190.dp)

                    .background(
                        shape = RoundedCornerShape(topStart = 200.dp),
                        color = Color(0xFFE5DBD0)
                    ),
            ) {
                MoreButtons(
                    groupsButton = {},
                    postsButton = {},
                    friendsButton = {
                        navController.navigate(Routes.FriendsScreen.route)
                        userInteractionViewmodel.getUsernames()
                    },
                    closeButton = {moreButton=false},
                    modifier = Modifier
                        .rowWeight(1.0f)
                        .columnWeight(1.0f)
                        .height(193.dp)
                        .width(193.dp)

                )
            }
        }

    }

}




@Composable
fun CurrentFriendsScreen(userInteractionViewmodel: UserInteractionViewmodel,navController: NavController){
    val friends by userInteractionViewmodel.friendsList.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        items(friends){
            var name by remember { mutableStateOf("") }
            userInteractionViewmodel.getUserInfo(it, name = { name = it },friends = {})
            Spacer(modifier = Modifier.height(10.dp))
            FriendsListItem(
                username = name,
                selectFriend = {
                    navController.navigate(Routes.UsersProfileScreen.route)
                    userInteractionViewmodel.currentSelectedUser(it)
                },
                removeFriendButton = {},
                property1 = Property1.Variant2,
                modifier = Modifier
                    .rowWeight(1.0f)
                    .columnWeight(1.0f)
                    .width(372.dp)
                    .height(65.dp)
            )
        }
    }
}








@Composable
fun findFriendsScreen(userInteractionViewmodel: UserInteractionViewmodel){
    SearchBarEdit(modifier = Modifier
        .fillMaxHeight(0.09f)
        .fillMaxWidth(0.92f)
        .rowWeight(1.0f)
        .columnWeight(1.0f),
        searchButton = {

        },
        userInteractionViewmodel)
}


@Composable
fun UsernamesResults(userInteractionViewmodel: UserInteractionViewmodel,navController: NavController){
    val matchingUsernames by userInteractionViewmodel.matchingUserNamesList.collectAsState()
    LazyColumn(modifier = Modifier.padding(top = 4.dp)){
        items(matchingUsernames){
            Row(modifier = Modifier
                .background(Color.White)
                .height(40.dp)
                .width(330.dp)
                .clickable {
                    navController.navigate(Routes.UsersProfileScreen.route)
                    userInteractionViewmodel.currentSelectedUser(it)
                },
                verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(text = it,fontFamily = com.calculator.bookfinder.accountbuttons.lindenHill, fontSize = 20.sp, modifier = Modifier)
            }
            Spacer(modifier = Modifier.padding(2.dp))

        }
    }
}













@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarEdit(modifier:Modifier,searchButton: () -> Unit = {},userInteractionViewmodel: UserInteractionViewmodel){
    val searchValue by userInteractionViewmodel.searchValue.collectAsState()
    val hasSearched by userInteractionViewmodel.hasSearched.collectAsState()
    TopLevel(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .width(320.dp)
        ) {
            //if the user starts typing, the back arrow appears
            if (hasSearched){
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
                    .clickable {
                        userInteractionViewmodel.hasNotSearched()
                        userInteractionViewmodel.clear()
                    }){
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .offset(x = 15.dp, y = 19.dp) // Adjust the offset values as needed
                    )
                }
            }
            TextField(
                modifier= Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .fillMaxHeight(),
                value = searchValue,
                onValueChange = {
                    userInteractionViewmodel.updateSearchValue(it)
                    userInteractionViewmodel.matchingUsernames(it)
                    userInteractionViewmodel.hasSearched()
                },
                placeholder = {
                    Text(text = "Search Usernames...",fontFamily = com.calculator.bookfinder.accountbuttons.lindenHill, fontSize = 20.sp, modifier = Modifier)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
            )
        }
        SearchButtonEdit(
            searchButton = searchButton,
            modifier = Modifier
                .boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 330.dp,
                        y = 12.dp
                    )
                )
                .height(35.dp)
                .width(38.dp)
        )
    }
}














































@Composable
fun FriendButtonEdit(
    modifier: Modifier = Modifier,
    buttonName: String = "",
    color:Long
) {
    TopLevelEdit(color,modifier = modifier) {
        FindFriendsEdit(
            buttonName = buttonName,
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
        )
    }
}





@Composable
fun FindFriendsEdit(
    buttonName: String,
    modifier: Modifier = Modifier
) {
    RelayText(
        content = buttonName,
        fontSize = 17.0.sp,
        fontFamily = lindenHill,
        color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        height = 1.347900390625.em,
        modifier = modifier
            .padding(
                paddingValues = PaddingValues(
                    start = 6.0.dp,
                    top = 5.0.dp,
                    end = 7.0.dp,
                    bottom = 4.0.dp
                )
            )
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
            .wrapContentHeight(
                align = Alignment.CenterVertically,
                unbounded = true
            )
    )
}

@Composable
fun TopLevelEdit(
    color: Long,
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(color),
        isStructured = false,
        radius = 5.0,
        content = content,
        modifier = modifier
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
            .relayDropShadow(
                color = Color(
                    alpha = 63,
                    red = 0,
                    green = 0,
                    blue = 0
                ),
                borderRadius = 5.0.dp,
                blur = 10.0.dp,
                offsetX = 0.0.dp,
                offsetY = 0.0.dp,
                spread = 5.0.dp
            )
    )
}