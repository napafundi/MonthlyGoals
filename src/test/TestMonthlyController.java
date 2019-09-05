/*
 * The MIT License
 *
 * Copyright 2019 Nick Pafundi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package test;


import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.MonthlyController;
import model.MonthlyTableModel;

class TestMonthlyController {
	MonthlyController controller;
	MonthlyTableModel model;
	String title;
	String desc;
	Date date;
	String result;

	@BeforeEach
	void setUp() throws Exception {
		controller = new MonthlyController(model);
	}

	@Test
	void testEmptyTitleReturnsErrorMessage() {
		title = "";
		result = controller.addGoal(title, date, desc);
		Assert.assertTrue("Please enter a goal title. (MAX 30 CHARACTERS)".contentEquals(result));
	}
	
	@Test
	void testLongTitleReturnsErrorMessage() {
		title = "Hello my name is Nick and I am writing a title that is too long and won't fit in the db.";
		result = controller.addGoal(title, date, desc);
		Assert.assertTrue("Please enter a goal title. (MAX 30 CHARACTERS)".contentEquals(result));
	}
	
	@Test
	void testEmptyDescReturnsErrorMessage() {
		title = "hello"; // title must be non-empty and length <= 30
		desc = "";
		result = controller.addGoal(title, date, desc);
		Assert.assertTrue("Please enter a goal description (MAX 60 CHARACTERS)".contentEquals(result));
	}
	
	@Test
	void testLongDescReturnsErrorMessage() {
		title = "hello"; // title must be non-empty and length <= 30
		desc = "Hello my name is Nick and I am writing a title that is too long and won't fit in the db becase the description can only be 60 char.";
		result = controller.addGoal(title, date, desc);
		Assert.assertTrue("Please enter a goal description (MAX 60 CHARACTERS)".contentEquals(result));
	}

}
