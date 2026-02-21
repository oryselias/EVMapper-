# EVMapper - Frontend (React)

This is the React/TypeScript frontend for EVMapper.

## Prerequisites

- Node.js 18+ installed
- Backend server running on port 8080 (see [backend-spring/README.md](../backend-spring/README.md))

## Installation

Install dependencies:

```bash
npm install
```

## Development

Run the development server:

```bash
npm run dev
```

This will start the frontend at `http://localhost:5173` (default Vite port).

## Build for Production

```bash
npm run build
```

The built files will be in the `dist` directory.

## Configuration

The frontend expects the backend API at `http://localhost:8080`. If your backend runs on a different port or URL, update the API configuration in the source code.

## Tech Stack

- React 18
- TypeScript
- Vite (build tool)
- Leaflet (maps)
- OpenStreetMap
