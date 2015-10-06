package com.codedojos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testNewVendingMachine_HasNoMoneyOrStock()
    {
        VendingMachine machine = new VendingMachine();
        assertEquals(machine.Nickels, 0);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 0);
        assertTrue(machine.Products.isEmpty());
    }

    public void testInsertingDime_CurrentGoesTo10_DimesGoesTo1()
    {
        VendingMachine machine = new VendingMachine();
        machine.insert(Coins.Dime);
        assertEquals(machine.Nickels, 0);
        assertEquals(machine.Dimes, 1);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 10);
        assertTrue(machine.Products.isEmpty());
    }

    public void testInsertingQuarter_CurrentGoesTo25_QuartersGoesTo1()
    {
        VendingMachine machine = new VendingMachine();
        machine.insert(Coins.Quarter);
        assertEquals(machine.Nickels, 0);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 1);
        assertEquals(machine.Current, 25);
        assertTrue(machine.Products.isEmpty());
    }

    public void testInsertingNickel_CurrentGoesTo5_NickelsGoesTo1()
    {
        VendingMachine machine = new VendingMachine();
        machine.insert(Coins.Nickel);
        assertEquals(machine.Nickels, 1);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 5);
        assertTrue(machine.Products.isEmpty());
    }

    public void testInsertingNickelDimeAndQuarter_AllGoTo1AndCurrentGoesTo40()
    {
        VendingMachine machine = new VendingMachine();
        machine.insert(Coins.Nickel);
        machine.insert(Coins.Dime);
        machine.insert(Coins.Quarter);
        assertEquals(machine.Nickels, 1);
        assertEquals(machine.Dimes, 1);
        assertEquals(machine.Quarters, 1);
        assertEquals(machine.Current, 40);
        assertTrue(machine.Products.isEmpty());
    }

    public void testServiceMachine_WithNoMoneyOrProducts_DoesntModifyNumbers()
    {
        VendingMachine machine = new VendingMachine();
        machine.service(0, 0, 0, new ArrayList<Product>());
        assertEquals(machine.Nickels, 0);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 0);
        assertTrue(machine.Products.isEmpty());
    }

    public void testServiceMachine_With10Nickels_Sets10Nickels()
    {
        VendingMachine machine = new VendingMachine();
        machine.service(10,0,0,new ArrayList<Product>());
        assertEquals(machine.Nickels, 10);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 0);
        assertTrue(machine.Products.isEmpty());
    }

    public void testServiceMachine_With10NickelsDimesAndQuarters_Sets10OfEach()
    {
        VendingMachine machine = new VendingMachine();
        machine.service(10,10,10,new ArrayList<Product>());
        assertEquals(machine.Nickels, 10);
        assertEquals(machine.Dimes, 10);
        assertEquals(machine.Quarters, 10);
        assertEquals(machine.Current, 0);
        assertTrue(machine.Products.isEmpty());
    }

    public void testSubsequentServicesDontAddTogether()
    {
        VendingMachine machine = new VendingMachine();
        machine.service(10,10,10,new ArrayList<Product>());
        machine.service(10,10,10,new ArrayList<Product>());
        assertEquals(machine.Nickels, 10);
        assertEquals(machine.Dimes, 10);
        assertEquals(machine.Quarters, 10);
        assertEquals(machine.Current, 0);
        assertTrue(machine.Products.isEmpty());
    }

    public void testServicedWith1AProduct_Has1AProduct()
    {
        VendingMachine machine = new VendingMachine();
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(Product.A());
        machine.service(0,0,0, products);
        assertEquals(machine.Nickels, 0);
        assertEquals(machine.Dimes, 0);
        assertEquals(machine.Quarters, 0);
        assertEquals(machine.Current, 0);
        assertEquals(1, machine.Products.size());
        assertEquals("A", machine.Products.get(0).Name);
    }

    public void testInsertedMoney_HitReturn_GetsMoneyBack()
    {
        VendingMachine machine = new VendingMachine();

        machine.insert(Coins.Dime, Coins.Nickel, Coins.Quarter);
        Coins[] coins = machine.coinReturn();

        assertEquals(3, coins.length);
        assertEquals(Coins.Quarter, coins[0]);
        assertEquals(Coins.Dime, coins[1]);
        assertEquals(Coins.Nickel, coins[2]);
    }

    public void test3DimesInserted_MachineContainsQuarterAndNickel_ReturnReturnsQuarterAndNickel()
    {
        VendingMachine machine = new VendingMachine();
        machine.service(1,0,1,new ArrayList<Product>());

        machine.insert(Coins.Dime, Coins.Dime, Coins.Dime);
        Coins[] coins = machine.coinReturn();

        assertEquals(2, coins.length);
        assertEquals(Coins.Quarter, coins[0]);
        assertEquals(Coins.Nickel, coins[1]);
    }
}
