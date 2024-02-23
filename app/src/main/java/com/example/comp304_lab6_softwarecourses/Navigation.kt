package com.example.comp304_lab6_softwarecourses

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.OptionScreen.route) {
            OptionScreen(navController = navController)
        }
        composable(
            Screen.DetailScreen.route + "/{description}",
            arguments = listOf(navArgument("description") { type = NavType.StringType })
        ) { entry ->
            DetailScreen(description = entry.arguments?.getString("description"), navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 5.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 40.dp)

        ) {
            Surface(
                color = Color.LightGray,
                border = BorderStroke(3.dp, color = Color.Black),
                shape = RoundedCornerShape(2.dp)
            ) {
                val url ="https://www.centennialcollege.ca/programs-courses/full-time/software-engineering-technology-online"
                Image(
                    painter = painterResource(id = R.drawable.cc_logo),
                    contentDescription = "Semester 4 Courses Image",
                    modifier = Modifier
                        .size(300.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                color = Color.LightGray,
                border = BorderStroke(3.dp, color = Color.Black),
                shape = RoundedCornerShape(7.dp),
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                val clickedColor = if (isPressed) Color.Blue else Color(69,69,70)
                Button(
                    onClick = {
                        navController.navigate(Screen.OptionScreen.route)
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .padding(all = 3.dp),
                    elevation = null,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = clickedColor
                    )

                ) {
                    val txtColor = if (isPressed) Color.Red else Color(212,223,56)
                    Text(
                        text = "SEM4 Software Courses",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = txtColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Composable
fun OptionScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(212, 223, 56))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            Text(
                text = "Centennial College\n SEM4 Courses",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(15.dp))

            SoftwareCourseItem("COMP-212: Programming 3        ") {
                navController.navigate(Screen.DetailScreen.withArgs("\nCOMP-212: Programming 3\n\nThe goal of this course is to enable students, already proficient in OOP, to build robust and more complex, data-driven desktop applications using the .NET technologies. Coursework emphasizes advanced topics, such as generics, extension methods, linear data structures, delegates, asynchronous programming, parallel programming, advanced GUI, Entity Framework core, ML.NET framework, etc. The language of instruction is C#."))
            }
            SoftwareCourseItem("COMP-216: Networking for Software Developers") {
                navController.navigate(Screen.DetailScreen.withArgs("\nCOMP-216 : Networking for Software Developers\n\nLearners in this course will gain hands-on experience by applying knowledge of network protocols and components to the development and maintenance of software applications. Coursework emphasizes network stacks, socket-based network applications, software-defined networks, and developing client applications that interface with various intelligent devices."))
            }
            Spacer(modifier = Modifier.height(1.dp))
            SoftwareCourseItem("COMP-254: Data Structures and Algorithms") {
                navController.navigate(Screen.DetailScreen.withArgs("\nCOMP-254 : Data Structures and Algorithms\n\nBuilding on fundamentals of Object-Oriented programming, this course exposes the students to algorithms and data structures. Students will analyze, evaluate and apply appropriate data structures & algorithms for the implementation of a software system. Coursework emphasizes the classical data structures, basic algorithm design, common operations on data structures, and the use of mathematical techniques to analyze the efficiency of the various algorithms. The languages of instruction are Java and Python (optional)."))
            }
            SoftwareCourseItem("COMP-304: Mobile Apps Development") {
                navController.navigate(Screen.DetailScreen.withArgs("\nCOMP-304 : Mobile Apps Development\n\nIn this mobile apps course, students will gain hands-on experience in developing and deploying mobile applications on the Android platform. Coursework emphasizes how to create advanced Graphical User Interfaces (GUIs), handle events, access remote services, store and retrieve data on the device, display maps, and use other Android APIs. Android Studio will be used to create a variety of mobile applications."))
            }
            SoftwareCourseItem("COMP-311: Software Testing and Quality Assurance") {
                navController.navigate(Screen.DetailScreen.withArgs("\nCOMP-311 : Software Testing and Quality Assurance\n\nThis course explores the goals of quality assurance and quality control activities performed during the life cycle of a software product. It focuses on integrating test processes with agile software development methodologies. Practical exercises give experience of design, specification, execution of tests plus test automation using tools through a mixture of instructor-directed exercises and student research leading to knowledge sharing."))
            }
            SoftwareCourseItem("Math-210: Linear Algebra and Statistics") {
                navController.navigate(Screen.DetailScreen.withArgs("\nMath-210 : Linear Algebra and Statistics\n\nThis course contains topics in Linear Algebra and Statistics. Linear algebra topics include operations with matrices, inverses, determinants, and vectors. Statistics topics include descriptive statistics, probability distributions as well as inferential statistics including hypothesis testing. Students will also use software applications in solving relevant problems."))
            }
        }
    }
}

@Composable
fun SoftwareCourseItem(courseName: String, onItemClick: () -> Unit) {
    Surface(
        color = Color.LightGray,
        border = BorderStroke(2.dp, color = Color.Gray),
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .padding(all = 2.dp)
    ){
    Text(
        text = courseName,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        color = Color.Black,
        fontFamily = FontFamily.Monospace,
        textAlign = TextAlign.Left,
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .padding(all = 16.dp)

    )
    }
}

@Composable
fun DetailScreen(description: String?, navController: NavController) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF454546))// Equivalent to RGB(69, 69, 70)
                .padding(horizontal = 16.dp),// Add some padding around the column
            verticalArrangement = Arrangement.SpaceBetween// This will push the button to the bottom
        ){
         Surface(
            color = Color.LightGray,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(3.dp),
             modifier = Modifier.weight(1f)// This makes the Surface fill the available space, pushing the button to the bottom
        ) {
            Text(
                text = "Course Description: $description",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Left,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()// Ensure the text fills the width of its container
                    .background(color = Color.LightGray)
                )
            }
            // Button outside the Surface, still within the Column
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            val clickedColor = if (isPressed) Color.Blue else Color(69, 69, 70)
            Button(
                onClick = {
                    navController.navigate(Screen.OptionScreen.route)
                },
                interactionSource = interactionSource,
                modifier = Modifier
                    .padding(all = 3.dp),
                elevation = null,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = clickedColor
                )

            ) {
                val txtColor = if (isPressed) Color.Red else Color(212, 223, 56)
                Text(
                    text = "Back to Software Courses",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    color = txtColor,
                    fontFamily = FontFamily.Monospace
                )
        }
    }
}


