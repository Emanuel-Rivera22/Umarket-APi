from pymongo import MongoClient
from faker import Faker
import random

# Conexión a MongoDB desde tu máquina local
client = MongoClient("mongodb://localhost:27017/")  # localhost en lugar de 'db'
db = client["umarket_db"]  # Base de datos

productos_collection = db["productos"]
usuarios_collection = db["usuarios"]
pedidos_collection = db["pedidos"]

fake = Faker('es_ES')

# -----------------------------
# PRODUCTOS
# -----------------------------
productos_base = [
    "huaweip20", "iphone15promax", "iphone17", "nokia3310",
    "oneplus12", "oneplus15", "oppoa5", "pixel8pro",
    "redmagic9pro", "s24ultra", "samsunga55", "xiaomi14pro", "xiaomi14ultra"
]

productos_data = []
for nombre in productos_base:
    producto = {
        "nombre": nombre.capitalize(),
        "descripcion": fake.text(max_nb_chars=120),
        "precio": round(random.uniform(400, 2000), 2),
        "imagenUrl": f"/images/{nombre}.jpg",
        "marca": nombre.split(' ')[0].capitalize(),
        "cpu": random.choice(["Snapdragon 8 Gen 3", "Apple A17", "Dimensity 9300", "Kirin 9000", "Exynos 2200", "MediaTek Dimensity 9200", "Tensor G3"]),
        "gpu": random.choice(["Adreno 750", "Apple GPU 6-core", "Mali-G715", "Immortalis-G720", "PowerVR GM9446", "ARM Mali-G78", "Adreno 730"]),
        "ram": random.choice([8, 12, 16, 24]),
        "almacenamiento": random.choice([32, 64, 128, 256, 512]),
        "pantalla": random.choice([6.1, 6.4, 6.7, 7.0]),
        "bateria": random.choice([3000, 4000, 4500, 5000, 5500])
    }
    productos_data.append(producto)

result = productos_collection.insert_many(productos_data)
productos_ids = result.inserted_ids
print(f"Insertados {len(productos_data)} productos.")

# -----------------------------
# USUARIOS (1,000)
# -----------------------------
usuarios_data = []
for _ in range(1000):
    user = {
        "userName": fake.user_name(),
        "userPassword": fake.password(length=8),
        "nombreCompleto": fake.name(),
        "email": fake.email(),
        "telefono": fake.phone_number(),
        "direccion": fake.address(),
        "ciudad": fake.city(),
        "pais": fake.country(),
        "rol": "USER",
        "metodosPago": random.sample(["Tarjeta", "Efectivo"], k=random.randint(1, 2)),
        "favoritos": random.sample(productos_ids, k=random.randint(0, 3)),
        "fotoPerfil": "/images/perfiles/default.png"
    }
    usuarios_data.append(user)

result = usuarios_collection.insert_many(usuarios_data)
usuarios_ids = result.inserted_ids
print(f"Insertados {len(usuarios_data)} usuarios.")

# -----------------------------
# PEDIDOS (15,000)
# -----------------------------
pedidos_data = []
for _ in range(15000):
    pedido = {
        "userId": str(random.choice(usuarios_ids)),
        "productos": random.sample(productos_data, k=random.randint(1, 5))
    }
    pedidos_data.append(pedido)

pedidos_collection.insert_many(pedidos_data)
print(f"Insertados {len(pedidos_data)} pedidos.")

print("\n¡Base de datos poblada con éxito!")
