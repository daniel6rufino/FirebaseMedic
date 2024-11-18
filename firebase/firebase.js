// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyB4-G0VLISrVfnxg7M-2BF-tpFeZBJXhOc",
  authDomain: "fir-medic.firebaseapp.com",
  projectId: "fir-medic",
  storageBucket: "fir-medic.firebasestorage.app",
  messagingSenderId: "814182124623",
  appId: "1:814182124623:web:d8b32d41c8593ecfc9ee3a",
  measurementId: "G-ZW4K5FY9SW"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);