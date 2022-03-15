package com.example.solver;

import java.lang.Math;

public class SolverFunctions
{
    public double calculerDiscriminant(double a, double b, double c)
    {
        return b * b - 4 * a * c;
    }

    public double calculerSolutionUne(double a, double b, double discriminant)
    {
        return - b - Math.sqrt(discriminant) / 2 * a;
    }

    public double calculerSolutionDeux(double a, double b, double discriminant)
    {
        return - b + Math.sqrt(discriminant) / 2 * a;
    }

    public double calculerSolutionDouble(double b, double c)
    {
        return - c / b;
    }
}
