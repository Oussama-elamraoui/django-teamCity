# Create your tests here.
from django.test import TestCase
from django.urls import reverse

class HelloApiTest(TestCase):
    def test_hello_endpoint(self):
        response = self.client.get(reverse('hello'))
        self.assertEqual(response.status_code, 200)
        self.assertEqual(response.json(), {"message": "Hello, world!"})