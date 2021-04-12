package com.example.composediet

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composediet.models.DishViewModel
import com.example.composediet.models.FoodHistoryViewModel
import com.example.composediet.models.FoodItemViewModel
import org.junit.Before
import org.junit.Rule

import org.junit.rules.TestRule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    val foodItemViewModel1 = FoodItemViewModel()
    val foodItemViewModel2 = FoodItemViewModel()
    val foodItemViewModel3 = FoodItemViewModel()
    val foodItemViewModel4 = FoodItemViewModel()
    val foodItemViewModel5 = FoodItemViewModel()

    val dishViewModel1 = DishViewModel()
    val dishViewModel2 = DishViewModel()

    @Before
    fun setUp() {
        foodItemViewModel1.onNameChange("a")
        foodItemViewModel1.onKilocaloriesChange(45)
        foodItemViewModel1.onProteinsChange(10)
        foodItemViewModel1.onFatsChange(34)
        foodItemViewModel1.onCarbohydratesChange(5)

        foodItemViewModel2.onNameChange("b")
        foodItemViewModel2.onKilocaloriesChange(10)
        foodItemViewModel2.onProteinsChange(22)
        foodItemViewModel2.onFatsChange(10)
        foodItemViewModel2.onCarbohydratesChange(15)

        foodItemViewModel3.onNameChange("c")
        foodItemViewModel3.onKilocaloriesChange(100)
        foodItemViewModel3.onProteinsChange(7)
        foodItemViewModel3.onFatsChange(7)
        foodItemViewModel3.onCarbohydratesChange(45)

        foodItemViewModel4.onWaterChange(30)
        foodItemViewModel4.onCarbohydratesChange(30)

        foodItemViewModel5.onWaterChange(15)
        foodItemViewModel5.onCarbohydratesChange(30)

        dishViewModel1.onProteinsChange(13)
        dishViewModel1.onKilocaloriesChange(500)
        dishViewModel1.onWaterChange(10)
        dishViewModel1.onFatsChange(20)

        dishViewModel2.onProteinsChange(7)
        dishViewModel2.onKilocaloriesChange(200)
        dishViewModel2.onWaterChange(10)
        dishViewModel2.onCarbohydratesChange(50)
        dishViewModel2.onFatsChange(3)
    }
    @Test
    fun test1_DishViewModel_adding_ingredients_1() {
        val dishViewModel = DishViewModel()
        dishViewModel.addIngredient(foodItemViewModel1)
        dishViewModel.addIngredient(foodItemViewModel2)
        dishViewModel.addIngredient(foodItemViewModel3)

        assertEquals("Proteins: ", 39, dishViewModel.proteins.value);
        assertEquals("Fats: ", 51, dishViewModel.fats.value);
        assertEquals("Carbohydrates: ", 65, dishViewModel.carbohydrates.value);
        assertEquals("Water: ", 0, dishViewModel.water.value);
        assertEquals("Kilocalories: ", 155, dishViewModel.kilocalories.value);
    }

    @Test
    fun test1_DishViewModel_adding_ingredients_2() {
        val dishViewModel = DishViewModel()
        dishViewModel.addIngredient(foodItemViewModel4)
        dishViewModel.addIngredient(foodItemViewModel5)

        assertEquals("Proteins: ", 0, dishViewModel.proteins.value);
        assertEquals("Fats: ", 0, dishViewModel.fats.value);
        assertEquals("Carbohydrates: ", 60, dishViewModel.carbohydrates.value);
        assertEquals("Water: ", 45, dishViewModel.water.value);
        assertEquals("Kilocalories: ", 0, dishViewModel.kilocalories.value);
    }

    @Test
    fun test1_DishViewModel_adding_ingredients_3() {
        val dishViewModel = DishViewModel()

        assertEquals("Proteins: ", 0, dishViewModel.proteins.value);
        assertEquals("Fats: ", 0, dishViewModel.fats.value);
        assertEquals("Carbohydrates: ", 0, dishViewModel.carbohydrates.value);
        assertEquals("Water: ", 0, dishViewModel.water.value);
        assertEquals("Kilocalories: ", 0, dishViewModel.kilocalories.value);
    }

    @Test
    fun test2_FoodHistoryViewModel_getFoodItemByDay_1() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 20, 10, 34, 12))
        foodHistoryViewModel.addFoodItem(foodItemViewModel2, LocalDateTime.of(2021, Month.MARCH, 10, 20, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel2, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 22, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel2, LocalDateTime.of(2021, Month.MARCH, 10, 12, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 11, 20, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 12, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 23, 43, 0))

        val foodItemsByDay = foodHistoryViewModel.getFoodItemsByDay(LocalDate.of(2021, Month.MARCH, 10))

        assertEquals("foodItemViewModel1 x1", 1, foodItemsByDay.filter { it.second == foodItemViewModel1 }.size);
        assertEquals("foodItemViewModel2 x2", 2, foodItemsByDay.filter { it.second == foodItemViewModel2 }.size);
    }

    @Test
    fun test2_FoodHistoryViewModel_getFoodItemByDay_2() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 20, 10, 34, 12))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 20, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 22, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.APRIL, 10, 12, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 11, 20, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 12, 23, 57))
        foodHistoryViewModel.addFoodItem(foodItemViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 23, 43, 0))

        val foodItemsByDay = foodHistoryViewModel.getFoodItemsByDay(LocalDate.of(2021, Month.APRIL, 10))

        assertEquals("foodItemViewModel1 x1", 1, foodItemsByDay.filter { it.second == foodItemViewModel1 }.size);
        assertEquals("foodItemViewModel2 x0", 0, foodItemsByDay.filter { it.second == foodItemViewModel2 }.size);
    }

    @Test
    fun test2_FoodHistoryViewModel_getFoodItemByDay_3() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        val foodItemsByDay = foodHistoryViewModel.getFoodItemsByDay(LocalDate.of(2021, Month.APRIL, 10))

        assertEquals("foodItemViewModel1 x0", 0, foodItemsByDay.filter { it.second == foodItemViewModel1 }.size);
        assertEquals("foodItemViewModel2 x0", 0, foodItemsByDay.filter { it.second == foodItemViewModel2 }.size);
    }

    @Test
    fun test3_FoodHistoryViewModel_getDishByDay_1() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 20, 10, 34, 12))
        foodHistoryViewModel.addDish(dishViewModel2, LocalDateTime.of(2021, Month.MARCH, 10, 20, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel2, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 22, 57))
        foodHistoryViewModel.addDish(dishViewModel2, LocalDateTime.of(2021, Month.MARCH, 10, 12, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 11, 20, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 12, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 23, 43, 0))

        val dishesByDay = foodHistoryViewModel.getDishesByDay(LocalDate.of(2021, Month.MARCH, 10))

        assertEquals("dishViewModel1 x1", 1, dishesByDay.filter { it.second == dishViewModel1 }.size);
        assertEquals("dishViewModel2 x2", 2, dishesByDay.filter { it.second == dishViewModel2 }.size);
    }

    @Test
    fun test3_FoodHistoryViewModel_getDishByDay_2() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 20, 10, 34, 12))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 20, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 22, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.APRIL, 10, 12, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 11, 20, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.FEBRUARY, 10, 12, 23, 57))
        foodHistoryViewModel.addDish(dishViewModel1, LocalDateTime.of(2021, Month.MARCH, 10, 23, 43, 0))

        val dishesByDay = foodHistoryViewModel.getDishesByDay(LocalDate.of(2021, Month.APRIL, 10))

        assertEquals("dishViewModel1 x1", 1, dishesByDay.filter { it.second == dishViewModel1 }.size);
        assertEquals("dishViewModel2 x0", 0, dishesByDay.filter { it.second == dishViewModel2 }.size);
    }

    @Test
    fun test3_FoodHistoryViewModel_getDishByDay_3() {
        val foodHistoryViewModel = FoodHistoryViewModel()

        val dishesByDay = foodHistoryViewModel.getDishesByDay(LocalDate.of(2021, Month.MARCH, 10))

        assertEquals("dishViewModel1 x1", 0, dishesByDay.filter { it.second == dishViewModel1 }.size);
        assertEquals("dishViewModel2 x2", 0, dishesByDay.filter { it.second == dishViewModel2 }.size);
    }
}