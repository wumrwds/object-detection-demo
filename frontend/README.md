# Frontend

## Directory Hierarchy

```
object-detection-system     # project name
|
|------- dist               # dist files output directory
|
|------- src
|		|
|       |---- api           # api directory 
|		|
|       |---- assets        # static resources
|		|
|       |---- components    # vue components for the main pages
|       |   	|---- common  # vue components for reuse
|       |---- css           # css
|       |---- routers       # router comfiguration
|       |    	|----index.js
|		|
|		|---- index.js        	# main index js script for Vue global configuration
|
|------ .editorconfig
|
|------ .eslintignore
|
|------ .eslintrc.js
|
|------ package.json				# dependencies configuration
|
|------ README.md
|
|------ webpack.common.js		# webpack common configurations
|
|------ webpack.dev.js      # webpack config for local development
|
|------ webpack.prd.js		 	# webpack config for production
```



## Run & Build

### Development Setup

```shell
# install devDependencies
$ npm install
```

### Run Server

```shell
# serve at localhost:8011
$ npm run start
```

### Build Dist Files

```shell
# build production dist files
npm run build_prd
```

