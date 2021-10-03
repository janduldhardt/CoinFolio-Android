# CoinFolio
Term Project CS3431 2/2021

## Disclamer
The repository is using git-secret to encrypt sensitive data.
To be able to build this you must add your own **Keys.kt** and **google-services.json**
The app is designed to work with https://www.cryptocompare.com/ API but a new service can be added.

## Abstract
CoinFolio is a personal guide to track your journey in the world of cryptocurrencies.
It is designed to track your portfolio with a nice, simple and clean UI with not much clutter.

## Features
* See the top 100 cryptocurrencies by marketcap
* Personal Wallet
  * Total balance in USD
  * Total profit
  * Each individual coins balance and profit
  * Track transactions
* Sync wallet with multiple devices via personal wallet seed

## Preview
<img src="https://user-images.githubusercontent.com/42645713/135746750-aa0cc76b-f67f-4cbe-8e68-50101087be36.png" width="200"> <img src="https://user-images.githubusercontent.com/42645713/135746218-67ce08ca-8d26-46d6-9150-6d664a1c6f1c.png" width="200"> <img src="https://user-images.githubusercontent.com/42645713/135746221-3d0f5903-1539-421f-81ca-de6096a5e366.png" width="200"> <img src="https://user-images.githubusercontent.com/42645713/135746227-23a8c600-d71c-4442-af1d-fe17d8d15c98.png" width="200"> <img src="https://user-images.githubusercontent.com/42645713/135746231-9a845e74-92b6-4032-b554-053122d4058d.png.png" width="200">

## API
https://www.cryptocompare.com/ (For cryptocurrency data)

https://firebase.google.com/ (To sync user wallet)

## 3rd party libraries
https://github.com/skydoves/PowerSpinner

https://github.com/bumptech/glide

https://github.com/square/retrofit

## App architecture
The app architecture used is based on best practices https://developer.android.com/jetpack/guide

<p align="center">
<img src="https://user-images.githubusercontent.com/42645713/135746908-bae398a4-de7d-42b7-80e7-2737479ceb3e.png" height="400">
 
 <img src="https://user-images.githubusercontent.com/42645713/135748299-b1a6b297-ead9-4ecb-9c77-ba21485b3b2d.png" height="400">

</p>
