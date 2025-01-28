package com.example.projectpam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectpam.ui.view.DestinasiHomeUtama
import com.example.projectpam.ui.view.HomeUtama
import com.example.projectpam.ui.view.dokterview.DestinasiDetailDokter
import com.example.projectpam.ui.view.dokterview.DestinasiEntryDokter
import com.example.projectpam.ui.view.dokterview.DestinasiHomeDokter
import com.example.projectpam.ui.view.dokterview.DestinasiUpdateDokter
import com.example.projectpam.ui.view.dokterview.DetailDokterView
import com.example.projectpam.ui.view.dokterview.HomeDokterScreen
import com.example.projectpam.ui.view.dokterview.InsertDokterView
import com.example.projectpam.ui.view.dokterview.UpdateDokterView
import com.example.projectpam.ui.view.hewanview.DestinasiDetailHewan
import com.example.projectpam.ui.view.hewanview.DestinasiEntryHewan
import com.example.projectpam.ui.view.hewanview.DestinasiHomeHewan
import com.example.projectpam.ui.view.hewanview.DestinasiUpdateHewan
import com.example.projectpam.ui.view.hewanview.DetailHewanView
import com.example.projectpam.ui.view.hewanview.HomeHewanScreen
import com.example.projectpam.ui.view.hewanview.InsertHewanView
import com.example.projectpam.ui.view.hewanview.UpdateHewanView
import com.example.projectpam.ui.view.jenishewanview.DestinasiDetailJenisHewan
import com.example.projectpam.ui.view.jenishewanview.DestinasiEntryJenisHewan
import com.example.projectpam.ui.view.jenishewanview.DestinasiHomeJenisHewan
import com.example.projectpam.ui.view.jenishewanview.DestinasiUpdateJenisHewan
import com.example.projectpam.ui.view.jenishewanview.DetailJenisHewanView
import com.example.projectpam.ui.view.jenishewanview.HomeJenisHewanScreen
import com.example.projectpam.ui.view.jenishewanview.InsertJenisHewanView
import com.example.projectpam.ui.view.jenishewanview.UpdateJenisHewanView
import com.example.projectpam.ui.view.perawatanview.DestinasiDetailPerawatan
import com.example.projectpam.ui.view.perawatanview.DestinasiEntryPerawatan
import com.example.projectpam.ui.view.perawatanview.DestinasiHomePerawatan
import com.example.projectpam.ui.view.perawatanview.DestinasiUpdatePerawatan
import com.example.projectpam.ui.view.perawatanview.DetailPerawatanView
import com.example.projectpam.ui.view.perawatanview.HomePerawatanScreen
import com.example.projectpam.ui.view.perawatanview.InsertPerawatanView
import com.example.projectpam.ui.view.perawatanview.UpdatePerawatanView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeUtama.route,
        modifier = Modifier
    )
    {
        // Home Utama route
        composable(DestinasiHomeUtama.route) {
            HomeUtama(
                onDokterClick = { navController.navigate(DestinasiHomeDokter.route) },
                onHewanClick = { navController.navigate(DestinasiHomeHewan.route) },
                onJenisHewanClick = { navController.navigate(DestinasiHomeJenisHewan.route) },
                onPerawatanClick = { navController.navigate(DestinasiHomePerawatan.route) }
            )
        }

        composable(DestinasiHomeDokter.route) {
            HomeDokterScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryDokter.route)
                },
                onDetailClick = { idDokter ->
                    if (idDokter.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailDokter.route}/$idDokter")
                    }
                }
            )
        }
        composable(DestinasiEntryDokter.route) {
            InsertDokterView(
                navigateBack = {
                    navController.navigate(DestinasiHomeDokter.route) {
                        popUpTo(DestinasiHomeDokter.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailDokter.route}/{idDokter}",
            arguments = listOf(navArgument("idDokter") { type = NavType.StringType })
        ) { backStackEntry ->
            val idDokter = backStackEntry.arguments?.getString("idDokter") ?: ""
            DetailDokterView(
                idDokter = idDokter,
                navigateBack = {
                    navController.navigate(DestinasiHomeDokter.route) {
                        popUpTo(DestinasiHomeDokter.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    navController.navigate("${DestinasiUpdateDokter.route}/$idDokter")
                }
            )
        }

        composable(
            route = "${DestinasiUpdateDokter.route}/{idDokter}",
            arguments = listOf(navArgument("idDokter") { type = NavType.StringType })
        ) { backStackEntry ->
            val idDokter = backStackEntry.arguments?.getString("idDokter") ?: ""
            UpdateDokterView(
                id_dokter = idDokter,
                navigateBack = {
                    navController.navigate(DestinasiHomeDokter.route) {
                        popUpTo(DestinasiHomeDokter.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(DestinasiHomeHewan.route) {
            HomeHewanScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryHewan.route)
                },
                onDetailClick = { idHewan ->
                    if (idHewan.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailHewan.route}/$idHewan")
                    }
                }
            )
        }
        composable(DestinasiEntryHewan.route) {
            InsertHewanView(
                navigateBack = {
                    navController.navigate(DestinasiHomeHewan.route) {
                        popUpTo(DestinasiHomeHewan.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailHewan.route}/{idHewan}",
            arguments = listOf(navArgument("idHewan") { type = NavType.StringType })
        ) { backStackEntry ->
            val idHewan = backStackEntry.arguments?.getString("idHewan") ?: ""
            DetailHewanView(
                idHewan = idHewan,
                navigateBack = {
                    navController.navigate(DestinasiHomeHewan.route) {
                        popUpTo(DestinasiHomeHewan.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    navController.navigate("${DestinasiUpdateHewan.route}/$idHewan")
                }
            )
        }

        composable(
            route = "${DestinasiUpdateHewan.route}/{idHewan}",
            arguments = listOf(navArgument("idHewan") { type = NavType.StringType })
        ) { backStackEntry ->
            val idHewan = backStackEntry.arguments?.getString("idHewan") ?: ""
            UpdateHewanView(
                idHewan = idHewan,
                navigateBack = {
                    navController.navigate(DestinasiHomeHewan.route) {
                        popUpTo(DestinasiHomeHewan.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Jenis Hewan Navigation
        composable(DestinasiHomeJenisHewan.route) {
            HomeJenisHewanScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryJenisHewan.route)
                },
                onDetailClick = { idJenisHewan ->
                    if (idJenisHewan.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailJenisHewan.route}/$idJenisHewan")
                    }
                }
            )
        }
        composable(DestinasiEntryJenisHewan.route) {
            InsertJenisHewanView(
                navigateBack = {
                    navController.navigate(DestinasiHomeJenisHewan.route) {
                        popUpTo(DestinasiHomeJenisHewan.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailJenisHewan.route}/{idJenisHewan}",
            arguments = listOf(navArgument("idJenisHewan") { type = NavType.StringType })
        ) { backStackEntry ->
            val idJenisHewan = backStackEntry.arguments?.getString("idJenisHewan") ?: ""
            DetailJenisHewanView(
                idJenisHewan = idJenisHewan,
                navigateBack = {
                    navController.navigate(DestinasiHomeJenisHewan.route) {
                        popUpTo(DestinasiHomeJenisHewan.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    navController.navigate("${DestinasiUpdateJenisHewan.route}/$idJenisHewan")
                }
            )
        }

        composable(
            route = "${DestinasiUpdateJenisHewan.route}/{idJenisHewan}",
            arguments = listOf(navArgument("idJenisHewan") { type = NavType.StringType })
        ) { backStackEntry ->
            val idJenisHewan = backStackEntry.arguments?.getString("idJenisHewan") ?: ""
            UpdateJenisHewanView(
                idJenisHewan = idJenisHewan,
                navigateBack = {
                    navController.navigate(DestinasiHomeJenisHewan.route) {
                        popUpTo(DestinasiHomeJenisHewan.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

                        composable(DestinasiHomePerawatan.route) {
                    HomePerawatanScreen(
                        navigateToItemEntry = {
                            navController.navigate(DestinasiEntryPerawatan.route)
                        },
                        onDetailClick = { idPerawatan ->
                            if (idPerawatan.isNotEmpty()) {
                                navController.navigate("${DestinasiDetailPerawatan.route}/$idPerawatan")
                            }
                        }
                    )
                }

                        composable(DestinasiEntryPerawatan.route) {
                    InsertPerawatanView(
                        navigateBack = {
                            navController.navigate(DestinasiHomePerawatan.route) {
                                popUpTo(DestinasiHomePerawatan.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                        composable(
                        route = "${DestinasiDetailPerawatan.route}/{idPerawatan}",
                arguments = listOf(navArgument("idPerawatan") { type = NavType.StringType })
            ) { backStackEntry ->
                val idPerawatan = backStackEntry.arguments?.getString("idPerawatan") ?: ""
                DetailPerawatanView(
                    idPerawatan = idPerawatan,
                    navigateBack = {
                        navController.navigate(DestinasiHomePerawatan.route) {
                            popUpTo(DestinasiHomePerawatan.route) {
                                inclusive = true
                            }
                        }
                    },
                    onClick = {
                        navController.navigate("${DestinasiUpdatePerawatan.route}/$idPerawatan")
                    }
                )
            }

            composable(
                route = "${DestinasiUpdatePerawatan.route}/{idPerawatan}",
                arguments = listOf(navArgument("idPerawatan") { type = NavType.StringType })
            ) { backStackEntry ->
                val idPerawatan = backStackEntry.arguments?.getString("idPerawatan") ?: ""
                UpdatePerawatanView(
                    idPerawatan = idPerawatan,
                    navigateBack = {
                        navController.navigate(DestinasiHomePerawatan.route) {
                            popUpTo(DestinasiHomePerawatan.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }