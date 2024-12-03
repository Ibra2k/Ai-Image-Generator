# Description

The App contains a Jetpack Compose Android app which generates AI generated images using a Python Flask Backend integration. The Python server makes an API request to a Hugging Face AI Model (stabilityai/stable-diffusion-3.5-large). With the fetched images we can use Flask and make API requests to the Python server (from our compose app using Retrofit & OkHttp) and get the images from there to display on the app.

# Purpose

The main purpose of the app is show how to implement Hugging Face AI models to Jetpack Compose Projects due to the new wave of AI models and lack of knowledge in the Android space.

# Requirements

Install using python terminal

```
pip install Flask flask-cors huggingface-hub python-dotenv Pillow
```
Create a .env file and add your huggingface api key, which can be copied from the access tokens on the huggingface website if you have an account.

# Running

1. In your python IDE terminal run `python main.py`. (which starts server)
2. In android studio start your emulator and the program
3. Enter a prompt you would like such as "Beautiful sunset over the horizon"
4. You will see the prompt being requested in your python terminal
5. Once in the loading screen, if the image does not load. Press back button and enter the prompt again. (PS: I think its a problem with warming up the server)

# Preview 

## Example of generated image that coverts all prompts to minecraft scenary (yes the game).
  ![Minecraft_SnowyMountains](https://github.com/user-attachments/assets/41b1b2fd-3a35-4f56-b419-513b06697e03)

## Example of a normal generated image with no prompt conversion on the source code (I get lonely sometimes).
  ![RL_Couple_Sunset](https://github.com/user-attachments/assets/89abb9e5-c31b-4c44-836c-fa1adbd706ff)
