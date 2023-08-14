BEGIN TRANSACTION;

DROP TABLE IF EXISTS account, recipe_user, recipe;

CREATE TABLE recipe (
	 recipe_id SERIAL NOT NULL,
	 title varchar(125),
	 instruction text,
	 servings int,
	 ingredients text,
	 PRIMARY KEY(recipe_id)
 );

CREATE TABLE recipe_user (
	user_id SERIAL NOT NULL
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(20),
	CONSTRAINT PK_recipe_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE account (
	account_id SERIAL NOT NULL
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_recipe_user FOREIGN KEY (user_id) REFERENCES recipe_user (user_id)
);

 --Chicken Alfredo
 INSERT INTO recipe(title,instruction,servings,ingredients)
 VALUES('Chicken Alfredo', 33, 'In a large skillet over medium-high heat, heat oil. Add chicken and season with salt and pepper. Cook until golden and cooked through, 8 minutes per side. Let rest 10 minutes, then slice. Add milk, broth, and garlic to skillet. Season with salt and pepper and bring to a simmer. Add fettuccine, stirring frequently for about 3 minutes. Let cook until al dente, 8 minutes more. Stir in heavy cream and Parmesan until combined. Simmer until sauce thickens. Remove from heat and stir in sliced chicken. Garnish with parsley.', 4, '2 tbsp. extra-virgin olive oil 2 boneless skinless chicken breasts Kosher saltFreshly ground black pepper 2 c. whole milk,  2 c. low-sodium chicken broth, 2 cloves garlic, minced, 8 oz. fettuccine,1/2 c. heavy cream, 1 c. freshly grated Parmesan, Freshly chopped parsley, for garnish');

--Flan
INSERT INTO recipe(title,instruction,servings,ingredients)
VALUES('Spanish Flan','Melt the sugar on the stove, then pour it into a round baking dish. Beat the eggs in a large bowl, then stir in the remaining ingredients. Pour the egg mixture into the baking dish. Cover with foil Bake for an hour in the preheated oven.', '8', 'sugar, eggs, sweetened condensed milk, Evaporated Milk, Vanilla');

--Another Recipe
INSERT INTO recipe(title,instruction,servings,ingredients)
VALUES('Coconut Flan','Preheat oven to 350 degrees F (175 degrees C). Place the sugar in a nonstick skillet, and slowly melt over low heat without stirring. Shake the pan by its handle to mix, but do not stir with a spoon. When the sugar has melted, pour into a 3 quart glass baking dish, coating the bottom. In a large bowl, beat the eggs with an electric mixer until smooth. Pour in the condensed milk, evaporated milk, coconut milk, whole milk, and vanilla; mix until well blended, about 2 minutes. Pour the egg mixture over the sugar in the pan. Place the pan inside of a larger dish and set in the oven. Fill the outer pan with water to 1 inch deep. Bake for 1 hour in the preheated oven, or until a toothpick inserted in the center comes out clean. Remove the flan from the water bath, and refrigerate until cold, 4 hours to overnight. To serve, run a knife around the edges of the dish to loosen the flan, then invert onto a serving dish.', '12', 'Eggs, Sweetened condensed milk, evaporated milk, coconut milk, whole milk, vanilla extract, sugar');



COMMIT TRANSACTION;
