from flask import Flask, request, jsonify
from flask_cors import CORS
from huggingface_hub import InferenceClient
import io
import base64
import logging
import os
from dotenv import load_dotenv



app = Flask(__name__)
CORS(app)
logging.basicConfig(level=logging.DEBUG)
load_dotenv()


# Initialize the Hugging Face client
client = InferenceClient(
    model="stabilityai/stable-diffusion-3.5-large",
    token=os.getenv("HUGGING_FACE_API_KEY"),
    timeout=180
)


@app.route('/generate-image', methods=['POST'])
def generate_image():
    if not request.is_json:
        return jsonify({"error": "Invalid Content-Type. Expected 'application/json'"}), 400

    try:
        data = request.get_json()
        if not data:
            return jsonify({"error": "Empty JSON body"}), 400

        prompt = data.get("prompt", "A beautiful sunset")
        logging.debug(f"Generating image for prompt: {prompt}")

        # Generate image
        image = client.text_to_image(prompt)

        # Convert image to base64
        buffered = io.BytesIO()
        image.save(buffered, format="PNG")
        img_str = base64.b64encode(buffered.getvalue()).decode("utf-8")

        return jsonify({"image": img_str})

    except KeyError:
        return jsonify({"error": "Missing 'prompt' key in JSON body"}), 400
    except Exception as e:
        logging.error(f"Error occurred: {e}")
        return jsonify({"error": f"Server error: {str(e)}"}), 500

if __name__ == "__main__":
    app.run(debug=True)
