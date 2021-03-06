/*******************************************************************************
 * Copyright (c) 2013 Thales Corporate Services SAS                             *
 * Author : Maciek Siemczyk                                                     *
 *                                                                              *
 * Permission is hereby granted, free of charge, to any person obtaining a copy *
 * of this software and associated documentation files (the "Software"), to deal*
 * in the Software without restriction, including without limitation the rights *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell    *
 * copies of the Software, and to permit persons to whom the Software is        *
 * furnished to do so, subject to the following conditions:                     *
 *                                                                              *
 * The above copyright notice and this permission notice shall be included in   *
 * all copies or substantial portions of the Software.                          *
 *                                                                              *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR   *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,     *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER       *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,*
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN    *
 * THE SOFTWARE.                                                                *
 *******************************************************************************/

package com.thalesgroup.hudson.plugins.xunit.service;

import com.thalesgroup.hudson.plugins.xunit.transformer.TempWorkspace;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Unit tests for XUnitValidationService class.
 *
 * @author Maciek Siemczyk
 */
public class XUnitValidationServiceTest {
    /**
     * Helper class for creating temporary workspace.
     */
    @Rule
    public TempWorkspace tempWorkspace = new TempWorkspace();

    /**
     * System Under Test (SUT).
     */
    private XUnitValidationService xUnitValidationService = new XUnitValidationService();

    @Test
    public void CheckFileIsNotEmpty_GivenEmptyFile_ReturnsFalse() throws Exception {
        File testFile = new File(tempWorkspace.getDir(), "empty.txt");
        boolean created = testFile.createNewFile();
        Assert.assertTrue(created);

        Assert.assertFalse(
                "CheckFileIsNotEmpty returned true.",
                xUnitValidationService.checkFileIsNotEmpty(testFile));
    }

    @Test
    public void CheckFileIsNotEmpty_GivenNotEmptyFile_ReturnsTrue() throws Exception {
        File testFile = CreateNotEmtyFile();

        Assert.assertTrue(
                "CheckFileIsNotEmpty returned false.",
                xUnitValidationService.checkFileIsNotEmpty(testFile));
    }

    /**
     * Helper method that will create a file with some text in it.
     *
     * @return Created file.
     * @throws Exception when there is a problem with writing to the file.
     */
    private File CreateNotEmtyFile() throws Exception {
        File testFile = new File(tempWorkspace.getDir(), "notempty.txt");

        FileOutputStream stream = new FileOutputStream(testFile);
        stream.write("This is just not empty test file!".getBytes());
        stream.close();

        return testFile;
    }
}
