# Deploying to Vercel

I've set up the `vercel.json` configuration for you. To complete the deployment, follow these steps:

### Option 1: Via Vercel Dashboard (Easiest)
1. Push your code to a GitHub/GitLab/Bitbucket repository.
2. Import the project in [Vercel](https://vercel.com/new).
3. In the **Build & Development Settings**, set the following:
   - **Framework Preset:** `Other`
   - **Build Command:** `./gradlew :webApp:wasmJsBrowserDistribution`
   - **Output Directory:** `webApp/build/dist/wasmJs/productionExecutable`
   - **Install Command:** `chmod +x gradlew`
4. Click **Deploy**.

### Option 2: Via Vercel CLI
If you have the Vercel CLI installed, run:
```bash
vercel --build-command "./gradlew :webApp:wasmJsBrowserDistribution" --output webApp/build/dist/wasmJs/productionExecutable
```

### Important Notes
- **Java Version:** Vercel builds use Java. If you get a "Unsupported class file major version" error, ensure you are using a Vercel Build Image that supports Java 17+. You can set this in Vercel project settings under "Node.js Version" (which often controls the build image) or use a GitHub Action for the build process if the Vercel environment is too restrictive.
- **Wasm Support:** The `vercel.json` I created ensures `.wasm` files are served with the correct MIME type.
