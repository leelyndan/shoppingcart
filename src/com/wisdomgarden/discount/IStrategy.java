package com.wisdomgarden.discount;

import com.wisdomgarden.domain.Cargo;

public interface IStrategy
{
    Double calculatePrice(Cargo cargo);
}
